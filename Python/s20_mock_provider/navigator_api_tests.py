import json
import unittest
from unittest.mock import patch

from hamcrest import assert_that, equal_to, less_than

from s20_mock_provider.distance import Distance
from s20_mock_provider.navigation_controller import app, nav
from s20_mock_provider.location import Location


class NavigatorAPITests(unittest.TestCase):
    def test_navigate_to_same_location_distance_is_zero(self):
        self.set_distance_to(0)
        location = Location("New York")
        self.set_start_point(location)
        self.set_destination(location)
        assert_that(self.distanceToDestination(), equal_to(0))

    def test_navigate_and_drive_to_another_location_distance_is_reduced(self):
        self.set_distances_to(2500, 1400)

        initialLocation = Location("New York")
        self.set_start_point(initialLocation)

        destination = Location("Los Angeles")
        self.set_destination(destination)

        initialDistance = self.distanceToDestination()

        midLocation = Location("Dallas")
        self.driveTo(midLocation)

        assert_that(self.distanceToDestination(), less_than(initialDistance))

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

    def distanceToDestination(self):
        response = self.app.get("/nav/distance")
        self.assertEqual(200, response.status_code)
        distance = Distance(response.get_json()['miles'])
        return distance.inKm()

    def driveTo(self, location):
        self.set_start_point(location)

    def set_distance_to(self, miles):
        nav.dist_provider.get_distance.return_value = Distance(miles)

    def set_distances_to(self, d1, d2):
        nav.dist_provider.get_distance.side_effect = [Distance(d1), Distance(d2)]

    def setUp(self):
        self.app = app.test_client(self)
        nav.dist_provider = patch('s20_mock_provider.distance_adapter.DistanceAdapter').start()


if __name__ == '__main__':
    unittest.main()
