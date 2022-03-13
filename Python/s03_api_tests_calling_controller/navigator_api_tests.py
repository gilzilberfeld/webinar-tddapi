import json
import unittest

from hamcrest import assert_that, equal_to, less_than

from s03_api_tests_calling_controller.navigation_controller import app
from s03_api_tests_calling_controller.distance import Distance
from s03_api_tests_calling_controller.location import Location


class NavigatorAPITests(unittest.TestCase):
    def test_navigate_to_same_location_distance_is_zero(self):
        location = Location("New York")
        self.set_start_point(location)
        self.set_destination(location)
        assert_that(self.distance_to_destination(), equal_to(0))

    def test_navigate_and_drive_to_another_location_distance_is_reduced(self):
        initialLocation = Location("New York")
        self.set_start_point(initialLocation)

        destination = Location("Los Angeles")
        self.set_destination(destination)

        initialDistance = self.distance_to_destination()

        midLocation = Location("Dallas")
        self.drive_to(midLocation)

        assert_that(self.distance_to_destination(), less_than(initialDistance))

    def set_start_point(self, location):
        response = self.app.post("/nav/startpoint",
                                 content_type='application/json',
                                 data=json.dumps(location.__dict__))
        self.assertEqual(200, response.status_code)

    def set_destination(self, location):
        response = self.app.post("/nav/destination",
                                 content_type='application/json',
                                 data=json.dumps(location.__dict__))
        self.assertEqual(200, response.status_code)

    def distance_to_destination(self):
        response = self.app.get("/nav/distance")
        self.assertEqual(200, response.status_code)
        distance = Distance(response.get_json()['miles'])
        return distance.inKm()

    def drive_to(self, location):
        self.set_start_point(location)

    def setUp(self):
        self.app = app.test_client(self)


if __name__ == '__main__':
    unittest.main()
