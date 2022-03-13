import unittest

from hamcrest import assert_that, equal_to, less_than

from s01_empty_api_tests.location import Location


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

		assert_that(self.distance_to_destination(), equal_to(less_than(initialDistance)))

	def set_start_point(self, location):
		pass

	def set_destination(self, location):
		pass

	def distance_to_destination(self):
		return -1

	def drive_to(self, midLocation):
		pass


if __name__ == '__main__':
	unittest.main()
