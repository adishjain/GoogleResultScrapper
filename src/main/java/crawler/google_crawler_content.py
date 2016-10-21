from readability import ParserClient
from googleapiclient.discovery import build


def extracting_content(url):
    parser_client = ParserClient(token='#########################')
    parser_response = parser_client.get_article(str(url))
    article = parser_response.json()
    str_article_title = article['title']
    strarticle = article['content']

    print(str_article_title)
    print (strarticle)



my_api_key = "##########################"
my_cse_id = "###########################"


def google_search(search_term, api_key, cse_id, **kwargs):
    print("hello")
    service = build("customsearch", "v1", developerKey="########################")
    res = service.cse().list(q=search_term, cx=cse_id, **kwargs).execute()
    return res['items']


print ("Enter your search query")
query=raw_input()
results = google_search(query , my_api_key, my_cse_id, num=10)
for result in results:
      extracting_content(result['link'])




