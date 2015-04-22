# OliveMediaSampleProject
A boilerplate android application with basic assets. Used as a base for a sample project for potential android developer. 

##The Project
Create a simple app that connects to an API and displays the results.
Use this project as a template to get you started. Fork this project, do your work, and then send us a link to the project on your github account when you're finished.

###App details
- Show a splash screen (based on the mockup) for 3 seconds (Bonus points for getting a custom progress bar and spin)
- Switch to a screen showing the details gathered from the API endpoint
- Periodically reload the details from the API (once every 30 seconds)
- Store the results client side, so if I reload the app and the API is failing, it will still display the previous results

####Assets
There are 2 folders for assets.
- assets - Contains the logo and the loader for the splash screen
- mockups - Contains the mockups of the 2 screens

####API details:
Url: http://sandbox.hiho.olive.media/meetings
- Returns a list of meetings happening in the next 4hrs
- It's a sliding window, it only shows the current 4hrs, anything outside of it is not returned
- Randomly throws a 500 status code, this is to simulate an API with a bad connection

Response structure:
{
	"list": [
		{
			"title": "This is the title",
			"location": "The gym",
			"time": "16:30"
		},
		{
			"title": "This is another title",
			"location": "The boardroom",
			"time": "18:00"
		}
	]
}

####What we're looking for from this code sample
- Code readability
- Modularity
- Consume a JSON API