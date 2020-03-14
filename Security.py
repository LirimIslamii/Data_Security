import argparse

parser = argparse.ArgumentParser()
parser.add_argument('-encode','--text',type=str, required=True ,help='Encode String')
args = parser.parse_args()

def encrypt(text):
    code = {'A':'.-', 'B':'-...', 
            'C':'-.-.', 'D':'-..', 'E':'.', 
            'F':'..-.', 'G':'--.', 'H':'....', 
            'I':'..', 'J':'.---', 'K':'-.-', 
            'L':'.-..', 'M':'--', 'N':'-.', 
            'O':'---', 'P':'.--.', 'Q':'--.-', 
            'R':'.-.', 'S':'...', 'T':'-', 
            'U':'..-', 'V':'...-', 'W':'.--', 
            'X':'-..-', 'Y':'-.--', 'Z':'--..', 
            '1':'.----', '2':'..---', '3':'...--', 
            '4':'....-', '5':'.....', '6':'-....', 
            '7':'--...', '8':'---..', '9':'----.', 
            '0':'-----', ', ':'--..--', '.':'.-.-.-', 
            '?':'..--..', '/':'-..-.', '-':'-....-', 
			'(':'-.--.', ')':'-.--.-',  ' ':' '
            }
            
    morse_code = ""

    for x in text:		
        	morse_code += code[x.upper()]
 															
    return morse_code


if __name__ == '__main__':
	print(encrypt(args.text))
