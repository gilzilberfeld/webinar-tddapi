class Distance:
    def __init__(self, miles):
        self.miles = miles

    def inKm(self):
        return int(self.miles * 1.6)
