
package br.com.delogic.asd.exception;

import org.springframework.util.Assert;

import br.com.delogic.jfunk.Has;

/**
 * Exception que será lançada caso alguma regra de negócio seja violada pelo
 * processo.
 *
 * @author Célio
 *
 */
@SuppressWarnings("serial")
public class BusinessException extends AsdException {

    private BusinessException parent;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException() {
        super(null);
    }

    public static BusinessException of(String message) {
        return new BusinessException(message);
    }

    /**
     * Retorna uma exception que faz equals com o tipo principal, permitindo
     * comparação com uma única instância.
     *
     * @param message
     * @param params
     * @return
     */
    private BusinessException withMessage(String message, Object... params) {
        BusinessException ex = new BusinessException(Has.content(params) ? String.format(message, params) : message) {
            @Override
            public boolean equals(Object obj) {
                return BusinessException.this == obj;
            }
        };
        ex.parent = this;
        return ex;
    }

    public BusinessException thrownIf(boolean condition) throws BusinessException {
        Assert.hasText(getMessage());
        if (condition) {
            throw this;
        }
        return this;
    }

    public BusinessException thrownIf(boolean condition, String message, Object... params) throws BusinessException {
        Assert.hasText(message);
        if (condition) {
            throw withMessage(message, params);
        }
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BusinessException)) {
            return false;
        }
        BusinessException that = (BusinessException) obj;
        return this == that || this == that.parent;
    }

    public boolean is(BusinessException ex) {
        return equals(ex);
    }

}
