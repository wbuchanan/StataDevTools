# Stata Development Tools
The initial goal of the project is to provide some analogous capabilities to the [devtools](https://github.com/hadley/devtools/) package in the [R language](https://cran.r-project.org).  Initially, this will focus on bringing GitHub integration to the [Stata](http://www.stata.com) [user community](http://www.statalist.org).  This can help to provide the analogous distribution method to the use of the `devtools::install_github()` function in R, while the official `install.packages()` function continues to provide users with a standard way of downloading/installing user contributed programs from the language's primary package repository.  

In the longer term, this may also include alternative methods for users to construct packages in a standardized way for GitHub (e.g., a specified directory structure that could then be used to facilitate automated project page generation and/or creation of help or other documentation, etc...).

# Additional Information
The major dependency for this project is currently the [GitHub Java API (org.eclipse.egit.github.core)](https://github.com/eclipse/egit-github/tree/master/org.eclipse.egit.github.core).  Although there are a few alternatives [GitHub API for Java (org.kohsuke.github)](https://github.com/kohsuke/github-api) and [JCabi GitHub API](http://github.jcabi.com/), the use of the [GitHub Mylyn Connector](https://github.com/eclipse/egit-github) mentioned previously is a deliberate choice based on the likelihood of sustained development and maintenance.  

