(function(){
    var app = angular.module('cost-manager-directives', []);

    app.directive("costDetails", function() {
      return {
        restrict: 'E',
        templateUrl: "partials/cost-details.html"
      };
    });

    app.directive("costTags", function() {
      return {
        restrict: 'E',
        templateUrl: "partials/cost-tags.html"
      };
    });

    app.directive("costTabs", function() {
      return {
        restrict: "E",
        templateUrl: "partials/cost-tabs.html",
        controller: function() {
          this.tab = 1;

          this.isSet = function(checkTab) {
            return this.tab === checkTab;
          };

          this.setTab = function(activeTab) {
            this.tab = activeTab;
          };
        },
        controllerAs: "tab"
      };
    });
  })();
