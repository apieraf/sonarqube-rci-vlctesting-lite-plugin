# Rules Compliance Index (RCI) Plugin for SonarQube 
*Requires SonarQube 6.7+*

This plugin calculates a metric based on the weighted value of issues and the number of lines of code (default settings are given in parentheses and can be configured on a global and per project level; other values are taken from the core metrics):

<pre>Issue weight = blocker violations &ast; weight (10)
             &plus; critical violations &ast; weight (5)
             &plus; major violations &ast; weight (3)
             &plus; minor violations &ast; weight (1)
             &plus; info violations &ast; weight (0)

Rules Compliance Index = max(1.0 - (Issue Weigth / Lines of Code) * 100, 0)</pre>

The plugin also adds a (configurable) Rules Compliance Rating metric to visualize the RCI (score an A-rating with a rules compliance index of 97%).


#### Installation

Install the plugin manually; copy the .jar file from the release to the `extensions/plugins` directory of your SonarQube installation.

#### References

* [VlcTesting 2018](http://www.vlctesting.es/)
* [VlcTesting Lite Everis - SonarQube Revealed: no es charla para novatos](https://www.iti.es/eventos/testing-lite-everis-sonarqube-revelead-no-es-charla-para-novatos/)
* [AmCharts](https://www.amcharts.com/)
* [SonarQube Extension Guide](https://docs.sonarqube.org/display/DEV)
* [SonarQube API 6.7](http://javadocs.sonarsource.org/6.7/apidocs/)
* [Rules Compliance Index Official Page](https://github.com/willemsrb/sonar-rci-plugin)