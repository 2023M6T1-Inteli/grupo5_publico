import React from 'react';

function RequestLossPercentage({value}) {
    const styles = {
        position: 'absolute',
        left: '64.7vw',
        alignSelf: 'center',

        fontFamily: 'Inter',
        fontStyle: 'normal',
        fontWeight: '300',
        lineHeight: '5px',
      };

      var x = value + '%' || '0%';

  return (
    <p style={styles} id='requestName'>{x}</p>
  );
}

export default RequestLossPercentage;