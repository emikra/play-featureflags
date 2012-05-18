Play Feature Flags
==================

This project is a module for playframework that enables you to easily define 'feature flags' by name, using a simple annotation on your controllers and/or a tag in your views.

Once defined, a feature will show up on an admin page provided by the module. This page gives an overview of all defined feature flags, their status and a On/Off button. When a feature is turned off, the module will intercept requests to the controller and return a 404 (Not Found), instead of rendering the normal content.

Feature flags are what flickr uses to be able to do 10+ deployments to production per day. See http://code.flickr.com/blog/2009/12/02/flipping-out/ for more info.

p(note). This module was originally written by Ben Verbeken and was initially modified to use a MongoDB backend (through Morphia), rather than JPA.

h2. Sample application

p(note). Your module should contain a sample app in the @sample@ directory that demonstrates the module. Describe how to run the demo here.

h2. Getting started

To install Deadbolt, you can use the modules repository:

	play install featureflags

h2. Configuration

Add the feature flags module to your application configuration.

	- play -> featureflags <version>

h2. Usage

h3. Tagging Views

	#{featureflags.feature 'featureName'}    
		<div>
		    ... feature content goes here ... 
		</div>
	#{/featureflags.feature}

Example: a Menu

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

h3. Annotating Controllers

h4. Method Level Annotation

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

h4. Class Level Annotation

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
