from s09_refactor_first_test.distance import Distance


class Navigator:
    def __init__(self, loc, dist_provider):
        self.dist_provider = dist_provider
        pass

    def getDistanceFromDestination(self):
        return Distance(0)

    def setDestination(self, dest):
        pass
