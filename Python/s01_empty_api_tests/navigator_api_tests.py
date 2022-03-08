import unittest

from hamcrest import assert_that, equal_to, less_than

from s01_empty_api_tests.location import Location


class NavigatorAPITests(unittest.TestCase):
	def test_navigate_to_same_location_distance_is_zero(self):
		location = Location("New York")
		self.setStartPoint(location)
		self.setDestination(location)
		assert_that(self.distanceToDestination(), equal_to(0))

	def test_navigate_and_drive_to_another_location_distance_is_reduced(self):
		initialLocation = Location("New York")
		self.setStartPoint(initialLocation)

		destination = Location("Los Angeles")
		self.setDestination(destination)

		initialDistance = self.distanceToDestination()

		midLocation = Location("Dallas")
		self.driveTo(midLocation)

		assert_that(self.distanceToDestination(), equal_to(less_than(initialDistance)))

	def setStartPoint(self, location):
		pass

	def setDestination(self, location):
		pass

	def distanceToDestination(self):
		return -1

	def driveTo(self, midLocation):
		pass


if __name__ == '__main__':
	unittest.main()
