(function(){
    var app = angular.module('cost-manager-controllers', []);

    app.controller('CostController', ['$http', function($http){
           var controller = this;
           controller.costs = [];
           $http.get('/cost-manager-web-services/rest/json/costservice/loadAll').success(function(data){
               controller.costs = data.costObjects;
           });
         }]);

         app.controller('TagController', function() {
           this.tag = {};

           this.addTag = function(cost) {
             cost.tags.push(this.tag);
             this.tag = {};
           };
     });
})();
