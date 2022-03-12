import unittest

from hamcrest import assert_that, raises, calling

from s05_first_failing_unit_tests.navigator import Navigator, NotInitialized


class NavigatorUnitTests(unittest.TestCase):
    def test_throw_when_navigating_without_starting_point(self):
        nav = Navigator()
        assert_that(calling(nav.get_distance_from_destination), raises(NotInitialized))


if __name__ == '__main__':
    unittest.main()
