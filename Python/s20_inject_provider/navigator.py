class Navigator:
    def __init__(self, dist_provider):
        self.dist_provider = dist_provider
        self.starting_point = None
        self.destination = None

    def get_distance_from_destination(self):
        return self.dist_provider.get_distance(self.starting_point, self.destination)

    def set_destination(self, dest):
        self.destination = dest

    def set_start_point(self, startingPoint):
        self.starting_point = startingPoint
