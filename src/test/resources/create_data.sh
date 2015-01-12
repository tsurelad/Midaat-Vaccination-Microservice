#!/bin/bash

curlMicroservice() {
    URL=$1
    DATA=$2
    curl -v -u admin:admin -H 'content-type: application/json' -X POST -d "$DATA" "http://localhost:8080$URL"
}

getCreatedId() {
    grep '"id"' | head -1 | sed 's/^.*"id".*\:.*"\(.*\)".*$/\1/'
}

addVaccinationDate() {
    NAME=$1
    DESCRIPTION=$2
    curlMicroservice "/vaccinationdates" "{\"name\":\"$NAME\",\"description\":\"$DESCRIPTION\"}"
}

addVaccination() {
    NAME=$1
    DESCRIPTION=$2
    curlMicroservice "/vaccinations" "{\"name\":\"$NAME\",\"description\":\"$DESCRIPTION\"}"
}

VD_1=$(addVaccinationDate "1 month" "" | getCreatedId)
VD_2=$(addVaccinationDate "2 months" "" | getCreatedId)
VD_4=$(addVaccinationDate "4 months" "" | getCreatedId)
VD_6=$(addVaccinationDate "6 months" "" | getCreatedId)
VD_12=$(addVaccinationDate "12 months" "" | getCreatedId)

V_LIVERB=$(addVaccination "Liver B" "" | getCreatedId)
V_PLATSET=$(addVaccination "Platset" "" | getCreatedId)
V_LIVERA=$(addVaccination "Liver A" "" | getCreatedId)
V_PAPILOMA=$(addVaccination "Papiloma" "" | getCreatedId)

echo "$VD_2"
echo "$V_LIVERA"

