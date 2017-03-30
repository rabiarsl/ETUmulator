/*
 * Copyright (C) 2017 Kasirgalabs
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.kasirgalabs.etumulator.processor;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.kasirgalabs.etumulator.linker.Symbol;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Singleton
public class GUISafeProcessor extends BaseProcessor implements Callable<Void> {
    private final ExecutorService executor;
    private Future<Void> future;
    private String code;
    private Set<Symbol> symbols;

    @Inject
    public GUISafeProcessor(ProcessorUnits processorUnits) {
        super(processorUnits);
        executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void run(String code, Set<Symbol> symbols) {
        this.code = code;
        this.symbols = symbols;
        future = executor.submit(this);
    }

    public void waitForComplete(long timeout, TimeUnit unit) throws InterruptedException,
            ExecutionException, TimeoutException {
        future.get(timeout, unit);
    }

    @Override
    public void stop() {
        super.stop();
        executor.shutdownNow();
    }

    @Override
    public Void call() throws Exception {
        super.run(code, symbols);
        return null;
    }
}
