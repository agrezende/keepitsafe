#!/bin/sh
#
# Copyright 2012 Felipe C. do R. P.
#
# This file is part of Keep It Safe.
# 
# Keep It Safe is free software: you can redistribute it and/or modify
# it under the terms of the GNU General Public License as published by
# the Free Software Foundation, either version 3 of the License, or
# (at your option) any later version.
# 
# Keep It Safe is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
# 
# You should have received a copy of the GNU General Public License
# along with Keep It Safe.  If not, see <http://www.gnu.org/licenses/>.


curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"Security", "description": "The security team passwords"}' http://localhost:8080/keepitsafe/keep
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"srv1", "description": "The first server", "login":"srv1-user", "password":"srv1-passwd"}' http://localhost:8080/keepitsafe/keep/1/secret
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"srv2", "description": "The second server"}' http://localhost:8080/keepitsafe/keep/1/secret
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"srv3", "description": "The third server"}' http://localhost:8080/keepitsafe/keep/1/secret
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"srv4", "description": "The fourth server"}' http://localhost:8080/keepitsafe/keep/1/secret

curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"Windows", "description" : "The windows Adm passwords"}' http://localhost:8080/keepitsafe/keep
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"AD", "description": "The AD password"}' http://localhost:8080/keepitsafe/keep/2/secret
	
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"DBA", "description" : "Database passwords"}' http://localhost:8080/keepitsafe/keep
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"ora1", "description": "The first oracle server"}' http://localhost:8080/keepitsafe/keep/3/secret
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"ora2", "description": "The second oracle server"}' http://localhost:8080/keepitsafe/keep/3/secret
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"ora3", "description": "The third oracle server"}' http://localhost:8080/keepitsafe/keep/3/secret
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"rac", "description": "The rac password"}' http://localhost:8080/keepitsafe/keep/3/secret
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"backup-1", "description": "The backup server"}' http://localhost:8080/keepitsafe/keep/3/secret
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"dba", "description": "The dba account"}' http://localhost:8080/keepitsafe/keep/3/secret

curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"Workstation", "description" : "Workstation local administrators"}' http://localhost:8080/keepitsafe/keep
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"ana", "description": "Ana workstation admin"}' http://localhost:8080/keepitsafe/keep/4/secret
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"bob", "description": "Bob workstation admin"}' http://localhost:8080/keepitsafe/keep/4/secret
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"charlie", "description": "Chalie workstation admin"}' http://localhost:8080/keepitsafe/keep/4/secret

curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"Personal", "description" : "My personal passwords" }' http://localhost:8080/keepitsafe/keep
curl -i -X POST -H 'Content-Type: application/json' -d '{"name":"gmail", "description": "My e-mail password"}' http://localhost:8080/keepitsafe/keep/5/secret
	
curl -i -X GET http://localhost:8080/keepitsafe/keep
