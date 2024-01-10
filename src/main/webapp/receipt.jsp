<html>
<head>
    <title>Review Payment</title>
    <style>
        body{
            font-family: Arial;
        }
        table{
            border: 0px;
        }
        table td{
            padding: 10px;
        }
    </style>
</head>
<body>
<div align="center">
    <h1>Payment Done. Thank you for purchasing out products</h1>
        <h3>Receipt Details</h3>
        <table>
            <tr>
                <td colspan="2">Merchant</td>
                <td>Company ABC Ltd.</td>
            </tr>
            <tr>
                <td colspan="2">Payer</td>
                <td>${payer.firstName} ${payer.lastName}</td>
            </tr>
            <tr>
                <td colspan="2">Shipping</td>
                <td>${transaction.amount.details.shipping }</td>
            </tr>
            <tr>
                <td colspan="2">Taxing</td>
                <td>${transaction.amount.details.tax }</td>
            </tr>
            <tr>
                <td colspan="2">Total</td>
                <td>${transaction.amount.total }</td>
            </tr>

        </table>
</div>
</body>
</html>
