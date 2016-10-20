# GoogleResultScrapper

[![Join the chat at https://gitter.im/GoogleResultScrapper/Lobby](https://badges.gitter.im/GoogleResultScrapper/Lobby.svg)](https://gitter.im/GoogleResultScrapper/Lobby?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
This plain java code uses JSoup to scrap the URLs of the search results.
##Features till now
1. Right now it just scrapes the front page results. These are limited to 10 for now.
2. Goes through each of the resulting URLs and scrapes the "p", "span", "strong", "b", "pre", "summary" tag within the page.

###Features to added soon
1. Maintaing the list of results from all the pages.
2. More tags to be tracked for the text.
3. Downloading images from the crawled webpages.
4. Maintain a separate file for each of the url. The file will contain text and images as well.
