#!/usr/bin/env bash
DEBUG1=${DEBUG1:-false}
test ${DEBUG1} == false && set +x
test ${DEBUG1} == true && python --version
test ! -s bin/activate && virtualenv .
test -s bin/activate && source bin/activate
pip install -r src/requirements.txt
test ${DEBUG1} == true && pip list
pushd src
make dev pack
