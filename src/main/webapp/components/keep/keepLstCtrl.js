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
       .controller('KeepLstCtrl', KeepLstCtrl);

function KeepLstCtrl() {

    var sf = this;

    sf.keeps = [{ 
        id:1,
        name: 'DB Servers',
        description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Impedit reprehenderit, eveniet pariatur tempora deserunt nobis, asperiores odio quasi dolorem. Eius soluta ullam atque nesciunt numquam enim, vitae recusandae animi aliquid! Lorem ipsum dolor sit amet, consectetur adipisicing elit. Soluta voluptates facilis delectus, magnam labore ullam rem voluptatum illo necessitatibus, deleniti, laborum praesentium ipsam eveniet expedita illum consectetur ducimus omnis tempore.',
        secrets: [{
            name: 'A1',
            username: 'A1',
            description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Distinctio perferendis voluptates culpa voluptas officiis iusto vero itaque quo tenetur commodi at eos, corporis architecto ducimus ipsa harum amet exercitationem nobis.'
        }]
    },{
        id:2,
        name: 'Critical Servers',
        description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error cupiditate veritatis laudantium possimus quia nulla ut accusantium, dolores consectetur tempora officiis nemo similique voluptates nostrum architecto, ea qui iste sapiente.'
    },{
        id:3,
        name: 'ABC',
        description: 'Lorem ipsum dolor sit amet, Lorem ipsum dolor sit amet, consectetur adipisicing elit. A maiores sint dolores quam ipsam optio quo nobis nisi illo soluta, neque unde porro quod ipsum assumenda laborum, numquam explicabo quasi.consectetur adipisicing elit. Error cupiditate veritatis laudantium possimus quia nulla ut accusantium, dolores consectetur tempora officiis nemo similique voluptates nostrum architecto, ea qui iste sapiente.'
    },{
        id:4,
        name: 'Administrators',
        description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error cupiditate veritatis laudantium possimus quia nulla ut accusantium, dolores consectetur tempora officiis nemo similique voluptates nostrum architecto, ea qui iste sapiente.'
    },{
        id:5,
        name: 'DMZ',
        description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error cupiditate veritatis laudantium possimus quia nulla ut accusantium, dolores consectetur tempora officiis nemo similique voluptates nostrum architecto, ea qui iste sapiente.'
    },{
        id:6,
        name: 'Local',
        description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error cupiditate veritatis laudantium possimus quia nulla ut accusantium, dolores consectetur tempora officiis nemo similique voluptates nostrum architecto, ea qui iste sapiente.'
    },{
        id:7,
        name: 'Xpto',
        description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error cupiditate veritatis laudantium possimus quia nulla ut accusantium, dolores consectetur tempora officiis nemo similique voluptates nostrum architecto, ea qui iste sapiente.'
    },{
        id:8,
        name: 'Zap',
        description: 'Lorem ipsum dolor sit amet, consectetur adipisicing elit. Error cupiditate veritatis laudantium possimus quia nulla ut accusantium, dolores consectetur tempora officiis nemo similique voluptates nostrum architecto, ea qui iste sapiente.'
    }];
}

})();
