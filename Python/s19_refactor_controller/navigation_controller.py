from flask import Flask, request, jsonify

from s19_refactor_controller.distance_adapter import DistanceAdapter
from s19_refactor_controller.location import Location
from s19_refactor_controller.navigator import Navigator

app = Flask(__name__)
distProvider = DistanceAdapter()
nav = Navigator(distProvider)


@app.route('/nav/destination', methods=['POST'])
def set_destination():
    content = request.json
    dest = Location(content['city'])
    nav.set_destination(dest)
    return '', 200


@app.route('/nav/startpoint', methods=['POST'])
def set_start_point():
    content = request.json
    start = Location(content["city"])
    nav.set_start_point(start)
    return '', 200


@app.route('/nav/distance', methods=['GET'])
def get_distance():
    return nav.get_distance_from_destination().__dict__
