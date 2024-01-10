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
    <h1>Please review before paying</h1>
    <form action="execute_payment" method="post">
        <table>
            <tr>
                <td><b>Transaction Details</b></td>
                <td><input type="text" name="paymentId" value="${param.paymentId}"></td>
                <td><input type="text" name="PayerId" value="${param.PayerID}"></td>
            </tr>
            <tr>
                <td colspan="2">Description</td>
                <td>${transaction.description}</td>
            </tr>
            <tr>
                <td colspan="2">Subtotal</td>
                <td>${transaction.amount.details.subtotal }</td>
            </tr>
            <tr>
                <td colspan="2">Shipping</td>
                <td>${transaction.amount.details.shipping }</td>
            </tr>
            <tr>
                <td colspan="2">Taxing</td>
                <td>${transaction.amount.details.tax }</td>
            </tr>

            <tr><td><br></td></tr>

            <tr>
                <td><b>Payer Information</b></td>
            </tr>

            <tr>
                <td colspan="2">First Name</td>
                <td>${payer.firstName}</td>
            </tr>
            <tr>
                <td colspan="2">Last Name</td>
                <td>${payer.lastName }</td>
            </tr>
            <tr>
                <td colspan="2">Email</td>
                <td>${payer.email}</td>
            </tr>


            <tr><td><br></td></tr>

            <tr>
                <td><b>Shipping Address</b></td>
            </tr>
            <tr>
                <td colspan="2">Recipient Name</td>
                <td>${shippingAddress.recipientName }</td>
            </tr>
            <tr>
                <td colspan="2">Line 1</td>
                <td>${shippingAddress.line1}</td>
            </tr>
            <tr>
                <td colspan="2">City</td>
                <td>${shippingAddress.city}</td>
            </tr>
            <tr>
                <td colspan="2">State</td>
                <td>${shippingAddress.state}</td>
            </tr>
            <tr>
                <td colspan="2">Postal Code</td>
                <td>${shippingAddress.postalCode}</td>
            </tr>


            <tr align="center">
                <td colspan="2"><input type="submit" name="total" value="Pay Now!"></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
