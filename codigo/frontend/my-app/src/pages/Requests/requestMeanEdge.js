import React from 'react';

function RequestMeanEdge({value}) {
    const styles = {
        position: 'absolute',
        left: '51vw',
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

export default RequestMeanEdge;