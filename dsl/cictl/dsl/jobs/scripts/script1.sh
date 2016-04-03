#!/usr/bin/env bash

die() {
    echo "[Error] " $*
    exit 1
}

warn() {
    echo "[Warning] " $*
}

info() {
    echo "[Info] " $*
}

debug() {
    test ${DEBUG1} == true && true || false
}

verify() {
    test ${1} == "error" && die $2 not set! || echo ${1}
}

DEBUG1=${DEBUG1:-false}
debug && set -x || set +x
debug && python --version || true

test ! -s bin/activate && virtualenv . || true
test -s bin/activate && source bin/activate || true
pip install --upgrade pip || true
pip install -r src/requirements.txt || true
debug && pip list || true

environment=$(verify ${environment:-error} environment)
type=$(verify ${type:-error} type)
target=$(verify ${target:-error} target)

info
info Make v2.arjs.net with ${environment} ${type} ${target}
info

pushd src > /dev/null
make ${environment} ${type} ${target}
test "${environment}" == "prod" && git checkout master && git pull && git push --tags || true

info
info Done.
info
