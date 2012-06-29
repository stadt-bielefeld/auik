#!/bin/bash

svn ls svn+ssh://cpearce@10.209.34.41/auik/tags | tail -n1 | tr -d '/'
# svn ls svn+ssh://$USER@svn.wald.intevation.org/auik/tags | tail -n1 | tr -d '/'