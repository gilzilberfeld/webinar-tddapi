import unittest
from unittest.mock import patch

from hamcrest import assert_that, equal_to

from s19_refactor_controller.distance import Distance
from s19_refactor_controller.location import Location
from s19_refactor_controller.navigator import Navigator


class NavigatorUnitTests(unittest.TestCase):
    def setUp(self):
        self.distProvider = patch('s19_refactor_controller.distance_adapter.DistanceAdapter').start()

    def test_when_created_distance_is_always_zero(self):
        self.set_distance_to(0)
        nav = Navigator(self.distProvider)
        nav.set_start_point(Location("London"))
        distance = nav.get_distance_from_destination()
        assert_that(distance.inKm(), equal_to(0))

    def test_distance_is_calculated_in_km(self):
        """2500 miles = 4000 kms"""
        self.set_distance_to(2500)
        startingPoint = Location("New York City")
        nav = Navigator(self.distProvider)
        nav.set_start_point(startingPoint)
        nav.set_destination(Location("Los Angeles"))
        distance = nav.get_distance_from_destination()
        assert_that(distance.inKm(), equal_to(4000))

    def test_provider_called_correctly(self):
        startingPoint = Location("New York City")
        dest = Location("Los Angeles")
        nav = Navigator(self.distProvider)
        nav.set_start_point(startingPoint)
        nav.set_destination(dest)
        nav.get_distance_from_destination()
        self.distProvider.get_distance.assert_called_with(startingPoint, dest)

    def set_distance_to(self, miles):
        self.distProvider.get_distance.return_value = Distance(miles)


if __name__ == '__main__':
    unittest.main()
