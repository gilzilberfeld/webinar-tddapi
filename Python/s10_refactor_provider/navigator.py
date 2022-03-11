from s10_refactor_provider.distance import Distance


class Navigator:
    def __init__(self, starting_point, dist_provider):
        self.dist_provider = dist_provider
        self.starting_point = starting_point

    def getDistanceFromDestination(self):
        return self.dist_provider.getDistance(self.starting_point, self.starting_point)

    def setDestination(self, dest):
        pass
