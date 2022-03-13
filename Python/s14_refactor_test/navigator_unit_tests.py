import unittest
from unittest.mock import patch

from hamcrest import assert_that, equal_to

from s14_refactor_test.distance import Distance
from s14_refactor_test.location import Location
from s14_refactor_test.navigator import Navigator


class NavigatorUnitTests(unittest.TestCase):
    def setUp(self) :
        self.distProvider = patch('s14_refactor_test.distance_adapter.DistanceAdapter').start()

    def test_when_created_distance_is_always_zero(self):
        self.set_distance_to(0)
        nav = Navigator(Location("London"), self.distProvider)
        distance = nav.get_distance_from_destination()
        assert_that(distance.inKm(), equal_to(0))

    def test_distance_is_calculated_in_km(self):
        """2500 miles = 4000 kms"""
        self.set_distance_to(2500)
        startingPoint = Location("New York City")
        nav = Navigator(startingPoint, self.distProvider)
        nav.set_destination(Location("Los Angeles"))
        distance = nav.get_distance_from_destination()
        assert_that(distance.inKm(), equal_to(4000))

    def set_distance_to(self, miles):
        self.distProvider.get_distance.return_value = Distance(miles)


if __name__ == '__main__':
    unittest.main()
