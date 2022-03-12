from flask import Flask, request

from s18_refactor_navigator.distance_adapter import DistanceAdapter
from s18_refactor_navigator.location import Location
from s18_refactor_navigator.navigator import Navigator

app = Flask(__name__)
nav: Navigator


@app.route('/nav/destination', methods=['POST'])
def set_destination():
    content = request.json
    dest = Location(content['city'])
    nav.set_destination(dest)


@app.route('/nav/startpoint', methods=['POST'])
def set_start_point():
    content = request.json
    start = Location(content["city"])
    distProvider = DistanceAdapter()
    nav = Navigator(start, distProvider)
    return ('', 200)

@app.route('/nav/distance', methods=['GET'])
def get_distance(self):
    return nav.get_distance_from_destination()
