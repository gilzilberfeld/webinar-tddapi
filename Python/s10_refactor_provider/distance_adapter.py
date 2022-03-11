from s10_refactor_provider.distance import Distance


class DistanceAdapter:
    def get_distance(self, start, dest):
        return Distance(0)