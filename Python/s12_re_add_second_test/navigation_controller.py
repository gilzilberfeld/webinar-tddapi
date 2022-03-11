from flask import Flask

app = Flask(__name__)


@app.route('/nav/destination', methods=['POST'])
def set_destination():
    pass


@app.route('/nav/startpoint', methods=['POST'])
def set_start_point():
    pass


@app.route('/nav/distance', methods=['GET'])
def get_distance():
    pass
