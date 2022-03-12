class Navigator:
    def __init__(self, starting_point, dist_provider):
        self.dist_provider = dist_provider
        self.starting_point = starting_point

    def get_distance_from_destination(self):
        return self.dist_provider.get_distance(self.starting_point, self.starting_point)

    def setDestination(self, dest):
        pass
