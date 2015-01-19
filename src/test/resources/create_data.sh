#!/bin/bash

curlMicroservice() {
    URL=$1
    DATA=$2
    curl -u admin:admin -H 'content-type: application/json' -X POST -d "$DATA" "http://localhost:8080$URL"
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

addCompound() {
    NAME=$1
    DESCRIPTION=$2
    curlMicroservice "/compounds" "{\"name\":\"$NAME\",\"description\":\"$DESCRIPTION\"}"
}

addCompoundInVaccinationDate() {
    COMPOUND_ID=$1
    VACCINATION_DATE_ID=$2
    DESCRIPTION=$3
    curlMicroservice "/compoundinvaccinationdates" "{\"compound\":{\"id\":\"$COMPOUND_ID\"},\"vaccinationDate\":{\"id\":\"$VACCINATION_DATE_ID\"},\"description\":\"$DESCRIPTION\"}"
}

addVaccinationInCompound() {
    COMPOUND_ID=$2
    VACCINATION_ID=$1
    DESCRIPTION=$3
    curlMicroservice "/vaccinationincompounds" "{\"compound\":{\"id\":\"$COMPOUND_ID\"},\"vaccination\":{\"id\":\"$VACCINATION_ID\"},\"description\":\"$DESCRIPTION\"}"
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

CIVD_1=$(addCompoundInVaccinationDate "$C_THEBEST1" "$VD_1" "" | getCreatedId)
CIVD_2=$(addCompoundInVaccinationDate "$C_THEBEST1" "$VD_6" "" | getCreatedId)
CIVD_3=$(addCompoundInVaccinationDate "$C_THEBEST1" "$VD_12" "" | getCreatedId)
CIVD_4=$(addCompoundInVaccinationDate "$C_COMPOUND2" "$VD_2" "" | getCreatedId)
CIVD_5=$(addCompoundInVaccinationDate "$C_COMPOUND2" "$VD_4" "" | getCreatedId)
CIVD_6=$(addCompoundInVaccinationDate "$C_THEEND3" "$VD_12" "" | getCreatedId)

VIC_1=$(addVaccinationInCompound "$V_LIVERB" "$C_THEBEST1" "" | getCreatedId)
VIC_2=$(addVaccinationInCompound "$V_LIVERB" "$C_COMPOUND2" "" | getCreatedId)
VIC_3=$(addVaccinationInCompound "$V_PLATSET" "$C_COMPOUND2" "" | getCreatedId)
VIC_4=$(addVaccinationInCompound "$V_LIVERA" "$C_THEBEST1" "" | getCreatedId)
VIC_5=$(addVaccinationInCompound "$V_PAPILOMA" "$C_THEBEST1" "" | getCreatedId)
VIC_6=$(addVaccinationInCompound "$V_PAPILOMA" "$C_THEEND3" "" | getCreatedId)


