from s10_refactor_provider.distance import Distance


class DistanceAdapter:
    def getDistance(self, start, dest):
        return Distance(0)