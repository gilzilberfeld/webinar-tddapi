import json
import unittest

from hamcrest import assert_that, equal_to, less_than

from s16_pass_unit_test.distance import Distance
from s16_pass_unit_test.navigation_controller import app
from s16_pass_unit_test.location import Location


class NavigatorAPITests(unittest.TestCase):
    def test_navigate_to_same_location_distance_is_zero(self):
        location = Location("New York")
        self.set_start_point(location)
        self.setDestination(location)
        assert_that(self.distanceToDestination(), equal_to(0))

    def test_navigate_and_drive_to_another_location_distance_is_reduced(self):
        initialLocation = Location("New York")
        self.set_start_point(initialLocation)

        destination = Location("Los Angeles")
        self.setDestination(destination)

        initialDistance = self.distanceToDestination()

        midLocation = Location("Dallas")
        self.driveTo(midLocation)

        assert_that(self.distanceToDestination(), equal_to(less_than(initialDistance)))

    def set_start_point(self, location):
        response = self.app.post("/nav/startpoint",
                                 content_type='application/json',
                                 data=json.dumps(location.__dict__))
        self.assertEqual(200, response.status_code)

    def setDestination(self, location):
        response = self.app.post("/nav/destination",
                                 content_type='application/json',
                                 data=json.dumps(location.__dict__))
        self.assertEqual(200, response.status_code)

    def distanceToDestination(self):
        response = self.app.get("/nav/distance")
        self.assertEqual(200, response.status_code)
        distance = Distance(response.get_json()['miles'])
        return distance.inKm()

    def driveTo(self, location):
        self.set_start_point(location)

    def setUp(self):
        self.app = app.test_client(self)


if __name__ == '__main__':
    unittest.main()
