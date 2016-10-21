# GoogleResultScrapper

[![Join the chat at https://gitter.im/GoogleResultScrapper/Lobby](https://badges.gitter.im/GoogleResultScrapper/Lobby.svg)](https://gitter.im/GoogleResultScrapper/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
This plain java code uses JSoup to scrap the URLs of the search results.
##Features till now
1. Right now it just scrapes the front page results. These are limited to 10 for now.
2. Goes through each of the resulting URLs and scrapes the "p", "span", "strong", "b", "pre", "summary" tag within the page.

##Features to added soon
1. Maintaing the list of results from all the pages.
2. More tags to be tracked for the text.
3. Downloading images from the crawled webpages.
4. Maintain a separate file for each of the url. The file will contain text and images as well.

###Using the python code
To run the code, you'll need to install 2 python modules:
####Readability API
    pip install readibility-api 
####google-api-python-client    
    pip install google-api-python-client

Also generate custom search API keys through the Google API dashboard.
Similarly for readiblity API ,generate keys by registering yourself in the readiblity dashboard.

Fill up the keys in the space provided in the code and you are good to go.
