#Rest API for Rules

1) To update rules engine

URL : http://localhost:8080/rules-memb-reading-list/updateRules
Method: GET

2) Apply rules

URL: http://localhost:8080/rules-memb-reading-list/applyRules
Method: POST
Body: 
          {
            "netWorth": 150,
            "liquidity": 7,
            "savings": 2,
            "expenses": 45,
            "housingDebt": 30,
            "nonHousingDebt": 20,
            "userId": 20156
          }
