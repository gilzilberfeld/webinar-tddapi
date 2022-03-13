from s20_mock_provider.distance import Distance


class DistanceAdapter:
    def get_distance(self, start, dest):
        return Distance(0)
