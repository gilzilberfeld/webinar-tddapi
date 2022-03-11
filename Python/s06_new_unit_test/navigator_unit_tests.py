import unittest

from hamcrest import assert_that, raises, calling, equal_to

from s06_new_unit_test.location import Location
from s06_new_unit_test.navigator import Navigator


class NavigatorUnitTests(unittest.TestCase):
    def test_when_created_distance_is_always_zero(self):
        loc = Location("London")
        nav = Navigator(loc)
        distance = nav.getDistanceFromDestination()
        assert_that(distance.inKm(), equal_to(0))


if __name__ == '__main__':
    unittest.main()
