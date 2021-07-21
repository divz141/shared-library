#!/bin/bash
DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"

echo
echo "Setting up Environment..."
echo
export BIN_DIR=${DIR}/bin
export DOCKER_DIR=${DIR}/docker
export LIB_DIR=${DIR}/lib
export XCLOUD_ENV=1
for x in ${BIN_DIR}; do
    case ":$PATH:" in
        *":$x:"*) :;; # already there
        *) PATH="$x:$PATH";;
    esac
done
if [ "$(readlink ${DIR:-.}/.git/hooks)" = ${LIB_DIR}/hooks ]
then
    echo "GIT hooks are already set up"
else
    rm -rf ${DIR:-.}/.git/hooks
    ln -s ${LIB_DIR}/hooks ${DIR:-.}/.git/hooks
fi

echo
echo "Setting up Environment... Done..."
echo
