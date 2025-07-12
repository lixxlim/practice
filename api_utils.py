import urllib.request
import urllib.parse

def call_api_get(url, access_token, query_string):
    encoded_query_string = urllib.parse.urlencode(query_string)
    full_url = url + "?keyword=" + encoded_query_string
    req = urllib.request.Request(full_url)
    req.add_header("X-ACCESS-TOKEN", access_token)

    with urllib.request.urlopen(req) as response:
        result = response.read.decode('utf-8')

    return result

def call_api_post(url, access_token, json_string):
    data_encoded = urllib.parse.urlencode(json_string).encode('utf-8')
    req = urllib.request.Request(url, data=data_encoded)
    req.add_header("Content-Type", "application/json")
    req.add_header("X-ACCESS-TOKEN", access_token)

    with urllib.request.urlopen(req) as response:
        result = response.read.decode('utf-8')

    return result
