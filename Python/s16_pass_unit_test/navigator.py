class Navigator:
    def __init__(self, starting_point, dist_provider):
        self.dist_provider = dist_provider
        self.starting_point = starting_point
        self.destination = None

    def getDistanceFromDestination(self):
        return self.dist_provider.get_distance(self.starting_point, self.destination)

    def setDestination(self, dest):
        self.destination = dest
