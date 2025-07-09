import urllib.request as request
import json
import uuid

if __name__ == "__main__":
    url = "https://challenge-server.tracks.run/hotel-reservation/hotels"
    headers = {
        "X-ACCESS-TOKEN": str(uuid.uuid4())
    }

    req = request.Request(url, headers=headers, method="GET")

    try:
        with request.urlopen(req) as res:
            data = res.read()
            res_body = data.decode('utf-8')
            res_json = json.loads(res_body)
            json_data = json.loads(data.decode("utf-8"))

            for hotel in json_data:
                print(hotel["name"])

    except request.error.URLError as e:
        print(f"URL ERROR: {e.reason}")
    except json.JSONDecodeError as e:
        print(f"JSON Parsing ERROR: {e.msg}")