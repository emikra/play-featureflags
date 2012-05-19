Play Feature Flags
==================

This project is a module for playframework that enables you to easily define 'feature flags' by name, using a simple annotation on your controllers and/or a tag in your views.

Once defined, a feature will show up on an admin page provided by the module. This page gives an overview of all defined feature flags, their status and a On/Off button. When a feature is turned off, the module will intercept requests to the controller and return a 404 (Not Found), instead of rendering the normal content.

Feature flags are what flickr uses to be able to do 10+ deployments to production per day. See http://code.flickr.com/blog/2009/12/02/flipping-out/ for more info.

This module was originally written by Ben Verbeken. This version has been modified to use a MongoDB backend (via PlayMorphia), rather than JPA.

Getting Started
===============

Add the following line to the `require` section of your `dependencies.yml` file:

    - emikra -> play-featureflags-morphia 1.0
        
Add the following to the `repositories` section of your `dependencies.yml` file:

    - emikra:
        type: http
        root: "http://emikra.github.com/play-featureflags/modules/"
        contains:
            - emikra -> *
            
Update your dependencies:

    $ play dependencies
    
or (for Eclipse-based projects):

    $ play eclipify --deps
    
The module is currently called `play-featureflags-morphia`, but this may change in the future.

Usage
=====

Tagging Views
-------------

```html
	#{featureflags.feature 'featureName'}    
		<div>
		    ... feature content goes here ... 
		</div>
	#{/featureflags.feature}
```

Example: a Menu

```html
	<ul id="menu">

		#{featureflags.feature 'demoMenu1'}
		    <li>Menu Item 1</li>
		#{/featureflags.feature}

		#{featureflags.feature 'demoMenu2'}
		    <li>Menu Item 2</li>
		#{/featureflags.feature}

		#{featureflags.feature 'demoMenu3'}
		    <li>Menu Item 3</li>
		#{/featureflags.feature}

		#{featureflags.feature 'demoMenu4'}
		    <li>Menu Item 4</li>
		#{/featureflags.feature}

	</ul>
```

Annotating Controllers
----------------------

### Method Level Annotation

```java
	public class MyController extends Controller {

	  @Feature("myFeature1")
	  public static void action1(){
		  // this action is available or not,
		  // depending on whether "myFeature1" is
		  // enabled
	  }

	  @Feature("myFeature2")
	  public static void action2(){
		  // this action is available or not,
		  // depending on whether "myFeature2" is
		  // enabled
	  }

	}
```

### Class Level Annotation

```java
	@Feature("myFeature")
	public class FeaturedController {

		// all actions in this controller will be
		// availabe or not, depending on whether
		// "myFeature" is enabled or not.

		public static void action1(){
		
		}

		public static void action1(){
		
		}

	}
```
