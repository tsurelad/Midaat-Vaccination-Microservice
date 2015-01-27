(function() {
    var app = angular.module('midaat', []);

    app.controller('MainController', function($scope, $http) {

        $scope.objects = [
            {key: 'vaccinations', dependent: {'compounds': 'vaccinationInCompounds'}, form: {}},
            {key: 'compounds', dependent: {vaccinations: 'vaccinationInCompounds', vaccinationDates: 'compoundInVaccinationDates'}, form: {}},
            {key: 'vaccinationDates', dependent: {compounds: 'compoundInVaccinationDates'}, form: {}}
        ];


        $scope.data = {
            vaccinations: [],
            compounds: [],
            vaccinationDates: []
        };
        $scope.active = null;


        $scope.credentials = {
            host: '',
            fullHost: '',
            user: '',
            password: '',
            auth: false
        };

        function _api(data) {
            var request = angular.extend({
                headers: {Authorization: 'Basic '+btoa($scope.credentials.user+':'+$scope.credentials.password)},
            }, data);

            if (request.url.match(/^\//)) request.url = $scope.credentials.fullHost + request.url;

            return $http(request);
        }

        function api(data) {
            return _api(data).error(function( response, status ) {
                alert('Server returned status '+status+', Details: '+(response.data && response.data.message || 'Unknown'));

                // The API response from the server should be returned in a
                // normalized format. However, if the request was not handled by the
                // server (or what not handles properly - ex. server error), then we
                // may have to normalize it on our end, as best we can.
                if (
                    ! angular.isObject( response.data ) ||
                    ! response.data.message
                    ) {

                    return( $q.reject( "An unknown error occurred." ) );

                }

                // Otherwise, use expected error message.
                return( $q.reject( response.data.message ) );
            });
        }

        function filterSchema(object, data) {
            var result = {};
            angular.forEach(data, function (value, key) {
                if (key == 'form' || $scope.data[key]) return;
                result[key] = value;
            });
            return result;
        }

        function load() {
            angular.forEach($scope.objects, function(object) {
                api({
                    method: 'GET',
                    url: '/' + object.key,
                    query: {size: 10000}
                }).success(function(response) {
                    $scope.data[object.key] = [];
                    var list;
                    try {
                        list = response._embedded[object.key];
                    } catch(e) { return console.log(e); }

                    angular.forEach(list, function(data) {
                        var entry = addEntry(object, filterSchema(object, data));

                        loadDependencies(object, entry);
                    });
                });
            });
        }

        function saveAddedDependency(object, entry, association, dependent, depentry) {
            var query = {};
            var depid = depentry.id;
            query[toSingular(dependent)+'Id'] = depid;
            query[toSingular(object.key)+'Id'] = entry.id;

            api({
                method: 'POST',
                url: '/' + association + '/create',
                params: query
            }).success(function (response, status, headers) {
                if (!response.id) {
                    return alert('Error with request');
                }

                depentry[object.key][entry.id]=response.id;
                depentry.form[object.key][entry.id]=true;
                entry[dependent][depid]=response.id;
            });

        }

        function saveRemovedDependency(object, entry, association, dependent, depentry) {
            var depid = depentry.id;
            var associd = entry[dependent][depid];
            if (!associd) return alert("Bad State, no "+association+" found!");

            api({
                method: 'DELETE',
                url: '/' + association + '/' + associd
            }).success(function (response, status, headers) {
                delete depentry[object.key][entry.id];
                delete depentry.form[object.key][entry.id];
                delete entry[dependent][depid];
            });

        }

        function saveDependencies(object, entry) {
            angular.forEach(object.dependent, function(association, dependent) {
                var original = entry[dependent];
                var modified = entry.form[dependent];

                var func = {};
                angular.forEach(modified, function(active, depid) {
                    if (active && !original[depid]) {
                        func[depid] = saveAddedDependency;
                    }
                });

                angular.forEach(original, function(active, depid) {
                    if (active && !modified[depid]) {
                        func[depid] = saveRemovedDependency;
                    }
                });

                angular.forEach($scope.data[dependent], function(depentry) {
                    if (func[depentry.id]) func[depentry.id](object, entry, association, dependent, depentry);
                });
            });
        }

        function toSingular(dependent) {
            return dependent.substr(0, dependent.length-1);
        }

        function loadDependencies(object, entry) {
            angular.forEach(object.dependent, function(association, dependent) {
                api({
                    method: 'GET',
                    url: '/' + object.key + '/' + entry.id + '/all' + dependent.charAt(0).toUpperCase() + dependent.substr(1),
                    query: {size: 10000}
                }).success(function(response) {
                    var added = {};
                    angular.forEach(response, function(data) {
                        var depid = data[toSingular(dependent)].id;
                        added[depid] = data.id;
                    });
                    angular.forEach($scope.data[dependent], function(depentry) {
                        if (added[depentry.id]) {
                            depentry[object.key][entry.id]=added[depentry.id];
                            depentry.form[object.key][entry.id]=true;
                            entry[dependent][depentry.id]=added[depentry.id];
                            entry.form[dependent][depentry.id]=true;
                        }
                    });
                });
            })
        }

        function addEntry(object, data) {
            angular.forEach(object.dependent, function(association, dependent) {
                data[dependent] = {};
            });
            var entry = angular.copy(data);
            entry.form = data;
            $scope.data[object.key].push(entry);
            return entry;
        }

        function clearActive(entry) {
            if (!$scope.active || entry && entry !== $scope.active) return;

            $scope.active.active = 0;
            $scope.active = null;
        }

        $scope.toggleActive = function (entry) {
            if (entry == null) {
                clearActive();
                return;
            }
            if (entry == $scope.active) {
                entry.active = 2;
                return;
            }

            clearActive();
            $scope.active = entry;
            entry.active = 1;
        };
        $scope.createForm = function (entry) {
            if (entry.form) return;
            entry.form = angular.copy(entry);
        };


        $scope.doUpdate = function (object, entry) {
            if (!entry.form.name) return alert('מלא לפחות שם');
            var data = filterSchema(object, entry.form);
            api({
                method: 'PUT',
                url: '/' + object.key + '/' + entry.id,
                data: data
            }).success(function (response, status, headers) {
                angular.extend(entry, data);
                saveDependencies(object, entry);
                entry.active = 1;
            });
        };

        $scope.doDelete = function (object, entry) {
            if (!confirm('באמת למחוק?')) return;

            api({
                method: 'DELETE',
                url: '/' + object.key + '/' + entry.id + '/delete'
            }).success(function (response, status, headers) {
                clearActive(entry);

                var index = -1;
                angular.forEach($scope.data[object.key], function(me, idx) {
                    if (me === entry) {
                        index = idx;
                        return false;
                    }
                });

                angular.forEach(object.dependent, function(association, dependent) {
                    angular.forEach($scope.data[dependent], function(depentry) {
                        delete depentry[object.key][entry.id];
                    });
                });

                if (index > -1) {
                    $scope.data[object.key].splice(index,1);
                }

            });
        };

        $scope.doCreate = function (object) {
            if (!object.form.name) return alert('מלא לפחות שם');

            var data = filterSchema(object, object.form);
            api({
                method: 'POST',
                url: '/' + object.key,
                data: data
            }).success(function (response, status, headers) {
                if (status == 201 && headers('Location')) {
                    data.id = headers('Location').replace(/^.*\//, '');
                } else {
                    return alert('Error with request');
                }

                var setActive = $scope.active === object.form;
                var entry = addEntry(object, data);
                angular.extend(entry.form, object.form); // add the associations
                object.form = {};
                saveDependencies(object, entry, $scope.data);

                if (setActive) {
                    clearActive();
                    $scope.toggleActive(entry);
                }
            });
        };

        function authenticate(success, error) {
            var cred = $scope.credentials;
            error = error || function(){};
            success = success || function(){};
            if (!cred.user || !cred.password) {
                return error('No user or password specified');
            }

            _api({
                method: 'GET',
                url: '/users'
            }).success(function(response, status) {
                cred.auth = true;
                success();
            }).error(function(response, status) {
                cred.auth = false;
                error('Server returned http status '+status);
            })
        }

        $scope.doAuth = function () {
            $scope.credentials.fullHost = $scope.credentials.host;
            if ($scope.credentials.fullHost && !$scope.credentials.fullHost.match(/^https?:/)) $scope.credentials.fullHost = location.protocol+"//" + $scope.credentials.fullHost;

            authenticate(function() {
                angular.forEach($scope.credentials, function(v,k) {
                   if (k == 'auth') return;
                   localStorage['auth_'+k] = v;
                });

                load();
            }, alert);
        };

        angular.forEach($scope.credentials, function(v,k) {
            $scope.credentials[k] = localStorage['auth_'+k] || '';
        });

    });
})();