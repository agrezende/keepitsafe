/*
 * Copyright (c) 2016 Felipe do Rego Pinto
 *
 * This file is part of Keep It Safe.
 * 
 * Keep It Safe is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Keep It Safe is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Keep It Safe.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 * The list of keeps controller
 * @author felipecrp
 */ 

(function() {

angular.module('keepItSafe')
       .controller('KeepCtrl', ['$routeParams', KeepCtrl]);

function KeepCtrl($rp) {

    var sf = this;

    sf.keep = {
        id: $rp.id,
        name: 'DB Servers',
        description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Impedit reprehenderit, eveniet pariatur tempora deserunt nobis, asperiores odio quasi dolorem. Eius soluta ullam atque nesciunt numquam enim, vitae recusandae animi aliquid! Lorem ipsum dolor sit amet, consectetur adipisicing elit. Soluta voluptates facilis delectus, magnam labore ullam rem voluptatum illo necessitatibus, deleniti, laborum praesentium ipsam eveniet expedita illum consectetur ducimus omnis tempore.',
        secrets: [{
            name: 'A1',
            username: 'A1',
            description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio perferendis voluptates culpa voluptas officiis iusto vero itaque quo tenetur commodi at eos, corporis architecto ducimus ipsa harum amet exercitationem nobis.',
            url: 'http://www.google.com'
        },{
            name: 'A1',
            username: 'A1',
            description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio perferendis voluptates culpa voluptas officiis iusto vero itaque quo tenetur commodi at eos, corporis architecto ducimus ipsa harum amet exercitationem nobis.',
            url: 'http://www.google.com'
        },{
            name: 'A1',
            username: 'A1',
            description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio perferendis voluptates culpa voluptas officiis iusto vero itaque quo tenetur commodi at eos, corporis architecto ducimus ipsa harum amet exercitationem nobis.',
            url: 'http://www.google.com'
        },{
            name: 'A1',
            username: 'A1',
            description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio perferendis voluptates culpa voluptas officiis iusto vero itaque quo tenetur commodi at eos, corporis architecto ducimus ipsa harum amet exercitationem nobis.',
            url: 'http://www.google.com'
        },{
            name: 'A1',
            username: 'A1',
            description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio perferendis voluptates culpa voluptas officiis iusto vero itaque quo tenetur commodi at eos, corporis architecto ducimus ipsa harum amet exercitationem nobis.',
            url: 'http://www.google.com'
        },{
            name: 'A1',
            username: 'A1',
            description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio perferendis voluptates culpa voluptas officiis iusto vero itaque quo tenetur commodi at eos, corporis architecto ducimus ipsa harum amet exercitationem nobis.',
            url: 'http://www.google.com'
        },{
            name: 'A1',
            username: 'A1',
            description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio perferendis voluptates culpa voluptas officiis iusto vero itaque quo tenetur commodi at eos, corporis architecto ducimus ipsa harum amet exercitationem nobis.',
            url: 'http://www.google.com'
        },{
            name: 'A1',
            username: 'A1',
            description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio perferendis voluptates culpa voluptas officiis iusto vero itaque quo tenetur commodi at eos, corporis architecto ducimus ipsa harum amet exercitationem nobis.',
            url: 'http://www.google.com'
        },{
            name: 'A1',
            username: 'A1',
            description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio perferendis voluptates culpa voluptas officiis iusto vero itaque quo tenetur commodi at eos, corporis architecto ducimus ipsa harum amet exercitationem nobis.',
            url: 'http://www.google.com'
        }]
    }
}

})();
