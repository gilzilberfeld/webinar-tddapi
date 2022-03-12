import unittest

from hamcrest import assert_that, equal_to

from s10_refactor_provider.location import Location
from s10_refactor_provider.navigator import Navigator
from s10_refactor_provider.distance_adapter import DistanceAdapter


class NavigatorUnitTests(unittest.TestCase):
    def test_when_created_distance_is_always_zero(self):
        loc = Location("London")
        distProvider = DistanceAdapter()
        nav = Navigator(loc, distProvider)
        distance = nav.get_distance_from_destination()
        assert_that(distance.inKm(), equal_to(0))

    # def test_distance_is_calculated_in_km(self):
    #     """2500 miles = 4000 kms"""
    #     startingPoint = Location("New York City")
    #     nav = Navigator(startingPoint)
    #     dest = Location("Los Angeles")
    #     nav.setDestination(dest)
    #     distance = nav.getDistanceFromDestination()
    #     assert_that(distance.inKm(), equal_to(4000))


if __name__ == '__main__':
    unittest.main()
