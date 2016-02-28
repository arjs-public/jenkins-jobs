#!/usr/bin/env bash

die() {
    echo "Error: " $*
    exit 1
}

warn() {
    echo "Warning: " $*
}

info() {
    echo "Info: " $*
}

debug() {
    test ${DEBUG1} == true && return true || return false
}

DEBUG1=${DEBUG1:-false}
debug && set -x || set +x
debug && python --version || true

test ! -s bin/activate && virtualenv . || true
test -s bin/activate && source bin/activate || true
pip install -r src/requirements.txt
debug && pip list || true

environment=${environment:-error}
test ${environment} == "error" && die environment not set! || true
type=${type:-error}
test ${type} == "error" && die type not set! || true
target=${target:-error}
test ${target} == "error" && die target not set! || true

info Make v2.arjs.net with ${environment} ${type} ${target}

pushd src
make ${environment} ${type} ${target}
test "${environment}" == "prod" && git checkout master && git pull && git push --tags || true

info Done.
