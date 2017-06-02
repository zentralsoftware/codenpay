# codenpay #

## Introduction ##
This is an implementation of COPYANDPAY 

## Quickstart ##
1. Create an Openshift account
2. Setup environment by executing the following commands
```python
rhc app create vitaminc jbossews-2.0
rhc cartridge add mongodb-2.4 -a vitaminc
```
3. Pull source code
```python
git remote add upstream git@github.com:zentralsoftware/codenpay.git
git pull -s recursive -X theirs upstream master
```
4. Deploy application
```python
git push
```

## Using codenpay ##
1. Open http://vitaminc-copynpay.rhcloud.com/
2. Choose menu "Checkout (1st Phase)"
3. Fill in the payment widget
4. Click "Pay now"
5. You should see a successful message
6. Choose menu "One Click (2nd Phase)"
7. Choose one of the registered payment method
8. Click "Pay now"
9. You should see a successful message
10. Click menu "Transaction"
11. You should see the history of transaction.

## License ##
codenpay is released under GNU General Public License. A copy of it is contained in the archives in a file named license.txt. A copy of the license can be found at http://www.gnu.org/copyleft/gpl.html.
