import unittest

from endpoint import app


class TestFlaskAPI(unittest.TestCase):

    def setUp(self):
        self.app = app.test_client(self)

    def test_status_and_response(self):
        response = self.app.get("/books")
        self.assertEqual(200, response.status_code)
        json = response.json
        self.assertEqual("Vernor Vinge", json[0]['author'])


if __name__ == '__main__':
    unittest.main()
