import React from 'react';

function RequestProductAmmount({value}) {
    const styles = {
        position: 'absolute',
        left: '16.5vw',
        alignSelf: 'center',

        fontFamily: 'Inter',
        fontStyle: 'normal',
        fontWeight: '300',
        lineHeight: '5px',
      };

      var x = value;

  return (
    <p style={styles} id='requestName'>{x}</p>
  );
}

export default RequestProductAmmount;