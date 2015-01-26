#!/bin/bash

curlMicroservice() {
    URL=$(echo "$1" | sed 's/ /%20/g')
    DATA=$2
    curl -u admin:admin -H 'content-type: application/json' -X POST -d "$DATA" "http://localhost:8080$URL"
}

getCreatedId() {
    grep '"id"' | head -1 | sed 's/^.*"id".*\:.*\([0-9]*\).*$/\1/'
}

addVaccinationDate() {
    NAME=$1
    DESCRIPTION=$2
    curlMicroservice "/vaccinationDates" "{\"name\":\"$NAME\",\"description\":\"$DESCRIPTION\"}"
}

addVaccination() {
    NAME=$1
    DESCRIPTION=$2
    curlMicroservice "/vaccinations" "{\"name\":\"$NAME\",\"description\":\"$DESCRIPTION\"}"
}

addCompound() {
    NAME=$1
    DESCRIPTION=$2
    curlMicroservice "/compounds" "{\"name\":\"$NAME\",\"description\":\"$DESCRIPTION\"}"
}

addCompoundInVaccinationDate() {
    COMPOUND_NAME=$1
    VACCINATION_DATE_NAME=$2
    DESCRIPTION=$3
    curlMicroservice "/compoundInVaccinationDates/createByNames?compoundName=$COMPOUND_NAME&vaccinationDateName=$VACCINATION_DATE_NAME&description=$DESCRIPTION" ""
}

addVaccinationInCompound() {
    COMPOUND_NAME=$2
    VACCINATION_NAME=$1
    DESCRIPTION=$3
    curlMicroservice "/vaccinationInCompounds/createByNames?compoundName=$COMPOUND_NAME&vaccinationName=$VACCINATION_NAME&description=$DESCRIPTION" ""
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

C_THEBEST1=$(addCompound "The best 1" "" | getCreatedId)
C_COMPOUND2=$(addCompound "compound 2" "" | getCreatedId)
C_THEEND3=$(addCompound "this is the end 3" "" | getCreatedId)

CIVD_1=$(addCompoundInVaccinationDate "The best 1" "1 month" "" | getCreatedId)
CIVD_2=$(addCompoundInVaccinationDate "The best 1" "6 months" "" | getCreatedId)
CIVD_3=$(addCompoundInVaccinationDate "The best 1" "12 months" "" | getCreatedId)
CIVD_4=$(addCompoundInVaccinationDate "compound 2" "2 months" "" | getCreatedId)
CIVD_5=$(addCompoundInVaccinationDate "compound 2" "4 months" "" | getCreatedId)
CIVD_6=$(addCompoundInVaccinationDate "this is the end 3" "12 months" "" | getCreatedId)

VIC_1=$(addVaccinationInCompound "Liver B" "The best 1" "" | getCreatedId)
VIC_2=$(addVaccinationInCompound "Liver B" "compound 2" "" | getCreatedId)
VIC_3=$(addVaccinationInCompound "Platset" "compound 2" "" | getCreatedId)
VIC_4=$(addVaccinationInCompound "Liver A" "The best 1" "" | getCreatedId)
VIC_5=$(addVaccinationInCompound "Papiloma" "The best 1" "" | getCreatedId)
VIC_6=$(addVaccinationInCompound "Papiloma" "this is the end 3" "" | getCreatedId)


